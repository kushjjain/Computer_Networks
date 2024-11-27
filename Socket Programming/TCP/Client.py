import socket

HOST = '127.0.0.1'
PORT = 5001

client_socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
client_socket.connect((HOST,PORT))

while True:
    client_message = input("Client: Enter message: ")
    client_socket.sendall(client_message.encode())
    if client_message.lower() == "exit":
        print("Client is closing")
        break
    print(f"Client message : {client_message}")
    
    server_message = client_socket.recv(1024).decode()
    if server_message.lower() == "exit":
        print("Server is disconnecting")
        break
    print(f"Server Message: {server_message}")
    
client_socket.close()