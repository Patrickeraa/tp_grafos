import json

fileName = input("Digite o nome do arquivo de entrada (com a extensão)\n>> ")

fIn = open(fileName)
fOut = open("output.txt", "w")

data = json.load(fIn)

de = []
para = []
peso = []

qntM = len(data['data']['nodes']['_data'])
qntN = len(data['data']['edges']['_data'])

N = data['data']['edges']['_data']

for i in range(len(N)):
  de.append(N[str(i+1)]['from'])
  para.append(N[str(i+1)]['to'])
  peso.append(N[str(i+1)]['label'])

fOut.write(str(qntM) + "\n")
for i in range(len(N)):
  fOut.write(str(de[i]) + " " + str(para[i]) + " " + str(float(peso[i])) + "\n")

fIn.close()

print("\n Arquivo de saída gerado.\n>> output.txt")
