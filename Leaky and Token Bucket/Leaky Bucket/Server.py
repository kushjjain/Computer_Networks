import socket, time

bucket_size = 10
current_level = 0

def leak_packets():
    global current_level
    leak_amount = 2
    if current_level > 0:
        leaked_size = min(leak_amount,current_level)
        current_level -= leaked_size
        print(f"Leaked {leak_amount} packets. Current level: {current_level}")
    else:
        print("No packets to leak.")

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server_socket.bind(('localhost', 12345))

while True:
    data, addr = server_socket.recvfrom(1024)
    packet_size = int(data.decode())
    
    
    if current_level + packet_size <= bucket_size:
        current_level += packet_size
        print(f"Received packet of size {packet_size}. Current level: {current_level}")
    else:
        print(f"Bucket overflow! Dropped packet of size {packet_size}")

    leak_packets()
    time.sleep(2)