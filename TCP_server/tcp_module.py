import socket
import sys

class TCP_module:

	def __init__(self):
		self.expectedClientSeq = 0
		self.serverSeq = 0
		self.state = 0
		self.initialSeq = 100
		self.initial = True


	def validNum(self, seqnum, acknum):
		if (self.initial):
			return True
		if (seqnum == self.expectedClientSeq and acknum == self.serverSeq):
			return True
		return False

	def validFlag(self, flag):
		if self.state == 0:
			return flag == "SYN"
		elif self.state == 1:
			return flag == "ACK"
		elif self.state == 2:
			return flag == "FIN" or flag == "MSG"
		else:
			return False;

	def createResponse(self, msg):
		flag = msg[0]
		seqnum = int(msg[1])
		acknum = int(msg[2])
		length = int(msg[3])
		response = ""

		if self.state == 0:
			self.state += 1
			self.initial = False
			self.serverSeq = self.initialSeq
			self.expectedClientSeq = seqnum + 1
			response = "SYNACK" + "/" + str(self.serverSeq) + "/" + str(self.expectedClientSeq)
			self.serverSeq += 1

		elif self.state == 1:
			self.state += 1
			response = ""
		
		elif self.state == 2:
			if flag == "MSG":
				inc = length
			elif flag == "FIN":
				inc = 1;
				self.state += 1
			else:
				return ""
			self.expectedClientSeq = seqnum + inc
			response = "ACK" + "/" + str(self.serverSeq) + "/" + str(self.expectedClientSeq)
			self.serverSeq += 1

		return response

	def initialize(self):
		self.state = 0
		self.initialSeq = 100
		self.initial = True

	def request(self, data):
		if data == "reset":
			self.__init__()
			return "RESET"

		msg = data[1:].split('/')
		reply = ""

		if len(msg) != 4:
			print "ERROR"
			print data
			print msg
			return "ERROR"

		#elif self.validNum(int(msg[1]), int(msg[2])):
		if self.validFlag(msg[0]):
			reply = self.createResponse(msg)
		if reply == "":
			reply = "X"

		return reply