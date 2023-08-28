#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>

// Implement your CRC verification logic here
int checkCRC(const char *message, const char *key) {
    char dividend[1024];
    strcpy(dividend, message);
    int keyLength = strlen(key);
    printf("%s\n", dividend);

    for (int i = 0; i <= strlen(dividend); ++i) {
        if (dividend[i] == '1' && i + keyLength < strlen(dividend) + 1) {

            for (int j = 0; j < keyLength; ++j) {
                dividend[i + j] = (dividend[i + j] == key[j]) ? '0' : '1';
            }
        }
        printf("%s\n", dividend);
    }
    printf("%s\n", dividend);
    // Check if remainder is all zeros
    for (int i = 0; i < strlen(dividend); ++i) {
        if (dividend[i] == '1') {
            return 0; // Remainder is not zero
        }
    }

    return 1; // Remainder is zero, message is correct
}

int main() {
    int server_socket_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket_fd == -1)
    {
        perror("Socket creation failed");
        return 1;
    }

    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(12345);
    server_addr.sin_addr.s_addr = INADDR_ANY;

    if (bind(server_socket_fd, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1)
    {
        perror("Binding failed");
        close(server_socket_fd);
        return 1;
    }

    if (listen(server_socket_fd, 1) == -1)
    {
        perror("Listening failed");
        close(server_socket_fd);
        return 1;
    }

    struct sockaddr_in client_addr;
    socklen_t client_len = sizeof(client_addr);
    int socket_fd = accept(server_socket_fd, (struct sockaddr *)&client_addr, &client_len);
    if (socket_fd == -1)
    {
        perror("Accepting connection failed");
        close(server_socket_fd);
        return 1;
    }

    char buffer[1024];
    ssize_t bytesReceived = recv(socket_fd, buffer, sizeof(buffer), 0);
    if (bytesReceived > 0) {
        buffer[bytesReceived] = '\0';
        const char *receivedData = buffer;

        if (checkCRC(receivedData, "1101")) {
            printf("Received data with correct CRC: %s\n", receivedData);
        } else {
            printf("Received data with incorrect CRC: %s\n", receivedData);
        }
    }


    close(socket_fd);
    close(server_socket_fd);
    return 0;
}
