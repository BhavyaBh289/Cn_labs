#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>

int main() {
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd == -1) {
        perror("Socket creation failed");
        return 1;
    }

    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(1245);
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    if (connect(sockfd, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1) {
        perror("Connection failed");
        close(sockfd);
        return 1;
    }

    char message[1024];

    while (1) {
        printf("Enter your message: ");
        fgets(message, sizeof(message), stdin);
        send(sockfd, message, strlen(message), 0); // Use send instead of sendto

        char buffer[1024];
        int bytes_received = recv(sockfd, buffer, sizeof(buffer), 0); // Use recv instead of recvfrom

        if (bytes_received <= 0) {
            if (bytes_received == 0) {
                printf("Server disconnected.\n");
            } else {
                perror("Receive failed");
            }
            break;
        } else {
            buffer[bytes_received] = '\0';
            printf("Server: %s\n", buffer);
        }
    }

    close(sockfd);
    return 0;
}
