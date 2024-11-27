import socket 

HOST= '127.0.0.1' 
PORT = 5001

server_socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
server_socket.bind((HOST,PORT))
server_socket.listen()
conn,addr = server_socket.accept()
print(f"Server is Listening on {HOST}:{PORT}")
print(f"Connected by {addr}")

while True:
    client_message = conn.recv(1024).decode()
    if client_message.lower() == "exit":
        print("Client Disconnected!!")
        break
    print(f"Client message: {client_message}")
    
    server_message = input("Server: Enter your message : ")
    conn.sendall(server_message.encode())
    if server_message.lower() == "exit":
        print("Server is closing the connection")
        break
    print(f"Server Message: {server_message}")
    
conn.close()
server_socket.close()