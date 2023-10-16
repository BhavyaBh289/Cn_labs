#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>

int main() {
    int server_socket_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket_fd == -1) {
        perror("Socket creation failed");
        return 1;
    }

    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(1245);
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    if (bind(server_socket_fd, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1) {
        perror("Binding failed");
        close(server_socket_fd);
        return 1;
    }

    if (listen(server_socket_fd, 1) == -1) {
        perror("Listening failed");
        close(server_socket_fd);
        return 1;
    }

    struct sockaddr_in client_addr;
    socklen_t client_len = sizeof(client_addr);
    int client_socket_fd = accept(server_socket_fd, (struct sockaddr *)&client_addr, &client_len);
    if (client_socket_fd == -1) {
        perror("Accepting connection failed");
        close(server_socket_fd);
        return 1;
    }

    while (1) {
        char buffer[1024];

        int bytes_received = recv(client_socket_fd, buffer, sizeof(buffer), 0);

        if (bytes_received < 0) {
            perror("Receive failed");
        } else {
            buffer[bytes_received] = '\0'; // Null-terminate the received data
            printf("Client: %s\n", buffer);
        }

        char message[1024];

        printf("Enter your message: ");
        fgets(message, sizeof(message), stdin);
        send(client_socket_fd, message, strlen(message), 0);
    }

    close(client_socket_fd);
    close(server_socket_fd);

    return 0;
}
