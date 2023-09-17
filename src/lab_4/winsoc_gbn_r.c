#include <stdio.h>
#include <stdlib.h>
#include <winsock2.h>
struct transmit{
    int n;
    char c;
};
int main(){
     WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0)
    {
        perror("WSAStartup failed");
        return 1;
    }

    SOCKET server_socket_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket_fd == INVALID_SOCKET)
    {
        perror("Socket creation failed");
        WSACleanup();
        return 1;
    }

    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(12345);
    server_addr.sin_addr.s_addr = INADDR_ANY;

    if (bind(server_socket_fd, (struct sockaddr *)&server_addr, sizeof(server_addr)) == SOCKET_ERROR)
    {
        perror("Binding failed");
        closesocket(server_socket_fd);
        WSACleanup();
        return 1;
    }

    if (listen(server_socket_fd, 1) == SOCKET_ERROR)
    {
        perror("Listening failed");
        closesocket(server_socket_fd);
        WSACleanup();
        return 1;
    }

    struct sockaddr_in client_addr;
    int client_len = sizeof(client_addr);
    SOCKET socket_fd = accept(server_socket_fd, (struct sockaddr *)&client_addr, &client_len);
    if (socket_fd == INVALID_SOCKET)
    {
        perror("Accepting connection failed");
        closesocket(server_socket_fd);
        WSACleanup();
        return 1;
    }
    int maxpackets,n,i;
    printf("Enter the number of packets  to receive");
    scanf("%d", &maxpackets);
    char packet [maxpackets];
    char data;
    i = 0;
    struct transmit t;
    while(i<maxpackets){
        if (recv(socket_fd, &t, sizeof(t), 0) != -1) {
            n=t.n;data=t.c;
            if(n==i+1){
                send(socket_fd, &n, sizeof(n), 0);
                printf("ack %d sent\n",n);
                packet[n-1]= data;
                i++;
            }
        }
    }
    for (int i = 0;i<maxpackets;i++){
        printf("\nThe %d packet's Data is %c ",i+1,packet[i]);
    }
    closesocket(socket_fd);
    closesocket(server_socket_fd);
    WSACleanup();


}

