#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>

int main() {
    int server_socket_fd = socket(AF_INET, SOCK_DGRAM, 0); // Use SOCK_DGRAM for UDP
    if (server_socket_fd == -1) {
        perror("Socket creation failed");
        return 1;
    }

    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(1245);
    server_addr.sin_addr.s_addr = INADDR_ANY; // Use INADDR_ANY for binding to all available interfaces

    if (bind(server_socket_fd, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1) {
        perror("Binding failed");
        close(server_socket_fd);
        return 1;
    }

    while (1) {
        struct sockaddr_in client_addr;
        socklen_t client_len = sizeof(client_addr);

        char buffer[1024];

        int bytes_received = recvfrom(server_socket_fd, buffer, sizeof(buffer), 0, (struct sockaddr *)&client_addr, &client_len);

        if (bytes_received < 0) {
            perror("Receive failed");
        } else {
            buffer[bytes_received] = '\0'; // Null-terminate the received data
            printf("Client: %s\n", buffer);
        }

        char message[1024];

        printf("Enter your message: ");
        fgets(message, sizeof(message), stdin);

        sendto(server_socket_fd, message, strlen(message), 0, (struct sockaddr *)&client_addr, client_len);
    }

    close(server_socket_fd);

    return 0;
}
