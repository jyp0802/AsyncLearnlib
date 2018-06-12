import socket
import threading
from tcp_module import TCP_module

bind_ip = ''
bind_port = 12345

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((bind_ip, bind_port))
server.listen(5)  # max backlog of connections

print 'Listening on {}:{}'.format(bind_ip, bind_port)


def handle_client_connection(client_socket, tid):
	tcp_instance = TCP_module()
	while True:
		req = client_socket.recv(1024)
		if req == "":
			client_sock.close()
			print "Finished\n"
			return
		req = req[:-2]
		print "Received " + req
		# print str(tid) + " " + req + "\n"
		output = tcp_instance.request(req)
		if output == "donotreply":
			client_socket.send("\n")
		else:
			client_socket.send(output+"\n")

tid = 1
while True:
	client_sock, address = server.accept()
	print 'Accepted connection from {}:{}'.format(address[0], address[1])
	client_handler = threading.Thread(
		target=handle_client_connection,
		args=(client_sock, tid,)  # without comma you'd get a... TypeError: handle_client_connection() argument after * must be a sequence, not _socketobject
	)
	client_handler.start()
	tid += 1