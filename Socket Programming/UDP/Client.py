import socket

HOST = '127.0.0.1' 
PORT = 5001        
client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

print("Connected to the server. Type 'exit' to close the connection.")

while True:
    client_message = input("Client: Enter a message to send: ")
    client_socket.sendto(client_message.encode(), (HOST, PORT))
    if client_message.lower() == 'exit':  
        print("Client is closing.")
        break

    data, server_address = client_socket.recvfrom(1024)
    server_message = data.decode()
    if server_message.lower() == 'exit':  
        print("Server closed the connection.")
        break
    print(f"Server: {server_message}")

client_socket.close()