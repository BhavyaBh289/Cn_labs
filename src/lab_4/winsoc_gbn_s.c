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

    SOCKET sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd == INVALID_SOCKET)
    {
        perror("Socket creation failed");
        WSACleanup();
        return 1;
    }

    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(12345);
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");

    if (connect(sockfd, (struct sockaddr *)&server_addr, sizeof(server_addr)) == SOCKET_ERROR)
    {
        perror("Connection failed");
        closesocket(sockfd);
        WSACleanup();
        return 1;
    }
    int maxpackets,n;
    printf("Enter the number of packets to Send");
    scanf("%d", &maxpackets);
    int winsize;
    printf("Enter the number of packets in a window: ");
    scanf("%d", &winsize);
    char packet [maxpackets];
    char data;
    for (int i = 0;i<maxpackets;i++){
        printf("Enter the %d packet's Data: ",i+1);
        scanf(" %c", &data);
        packet[i] = data;
    }
    int ack = 0;
    int i = 0;
    struct transmit t;
    while(ack<maxpackets){
        if(i-ack<winsize&& i<maxpackets){
            i++;
            printf("\npacket %d send  with data %c",i,packet[i-1]);
            t.n=i;t.c=packet[i-1];
            send(sockfd, &t, sizeof(t), 0);

        }
        if (recv(sockfd, &n, sizeof(n), 0) != -1) {
            if(n==ack+1){
                ack++;
            }else{
                i = ack;
            }
        }
    }

    closesocket(sockfd);
    WSACleanup();

}
