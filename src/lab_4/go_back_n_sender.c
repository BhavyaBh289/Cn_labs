#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>

int main(){
    // int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    // if (sockfd == -1){
    //     perror("Socket creation failed");
    //     return 1;
    // }
    //
    // struct sockaddr_in server_addr;
    // server_addr.sin_family = AF_INET;
    // server_addr.sin_port = htons(1245);
    // server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    //
    // if (connect(sockfd, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1){
    //     perror("Connection failed");
    //     close(sockfd);
    //     return 1;
    // }
    int maxpackets,n;
    printf("Enter the number of packets ");
    scanf("%d", &maxpackets);
    int winsize;
    printf("Enter the number of packets in a window: ");
    scanf("%d", &winsize);
    char packet [maxpackets];
    char data;
    for (int i = 0;i<maxpackets;i++){
        printf("Enter the %d packet's Data: ",i);
        scanf(" %c", &data);
        packet[i] = data;
    }
    int ack = -1;
    int i = -1;
    while(ack<maxpackets-1){
        // printf("%d %d %d",i,maxpackets,ack);
        if(i-ack<winsize&& i<maxpackets){
            i++;
            printf("packet %d send  with data %c",i,packet[i]);
        }
        scanf("%d",&n);
        if (n!=-1){
            // printf("%d %d %d",i,n,ack);
            if(n==ack+1){
                ack++;
            }else{
                i = ack;
            }
            // printf("%d %d %d",i,n,ack);
        }
    }
}