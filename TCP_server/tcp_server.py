import socket
import threading
from tcp_module import TCP_module

bind_ip = ''
bind_port = 12345

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((bind_ip, bind_port))
server.listen(5)  # max backlog of connections

print 'Listening on {}:{}'.format(bind_ip, bind_port)

length = None
buffer = ""
eq_buffer = ""

client_socket, address = server.accept()
print 'Accepted connection from {}:{}'.format(address[0], address[1])
tcp_instance = TCP_module()

while True:
	data = client_socket.recv(4096)
	if not data:
		break
	buffer += data
	
	while True:
		if length is None:
			if ':' not in buffer:
				break
			# remove the length bytes from the front of buffer
			# leave any remaining bytes in the buffer!
			length_str, ignored, buffer = buffer.partition(':')
			length = int(length_str)

		if len(buffer) < length:
			break
		# split off the full message from the remaining bytes
		# leave any remaining bytes in the buffer!
		message = buffer[:length]
		message = message.rstrip()
		buffer = buffer[length:]
		length = None
		# PROCESS MESSAGE HERE
		output = tcp_instance.request(message)
		x = message[0]
		if x=='t':
			pass
		elif x=='e':
			eq_buffer += output + "-"
		elif x=='f':
			eq_buffer += output
			# print eq_buffer
			client_socket.send(eq_buffer+"\n")
			eq_buffer = ""
		else:
			client_socket.send(output+"\n")
