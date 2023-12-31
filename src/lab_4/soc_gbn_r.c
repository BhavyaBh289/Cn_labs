#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>
struct transmit{
    int n;
    char c;
};
int main(){
    int server_socket_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket_fd == -1){
        perror("Socket creation failed");
        return 1;
    }

    struct sockaddr_in server_addr;
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(1245);
    server_addr.sin_addr.s_addr =inet_addr("127.0.0.1");

    if (bind(server_socket_fd, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1){
        perror("Binding failed");
        close(server_socket_fd);
        return 1;
    }

    if (listen(server_socket_fd, 1) == -1){
        perror("Listening failed");
        close(server_socket_fd);
        return 1;
    }

    struct sockaddr_in client_addr;
    socklen_t client_len = sizeof(client_addr);
    int socket_fd = accept(server_socket_fd, (struct sockaddr *)&client_addr, &client_len);
    if (socket_fd == -1){
        perror("Accepting connection failed");
        close(server_socket_fd);
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
    close(socket_fd);
    close(server_socket_fd);

}

