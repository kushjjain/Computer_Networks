import socket

HOST = '127.0.0.1'  
PORT = 5001        

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server_socket.bind((HOST, PORT))

print(f"Server is listening on {HOST}:{PORT}")

while True:
    data, client_address = server_socket.recvfrom(1024)
    client_message = data.decode()
    if client_message.lower() == 'exit': 
        print("Client disconnected.")
        break
    print(f"Client ({client_address}): {client_message}")

    server_message = input("Server: Enter a message to send: ")
    server_socket.sendto(server_message.encode(), client_address)
    if server_message.lower() == 'exit':
        print("Server is closing.")
        break
    
server_socket.close()