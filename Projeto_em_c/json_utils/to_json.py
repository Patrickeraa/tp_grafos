fileName = input("Digite o nome do arquivo de entrada (com a extensão)\n>> ")

fIn = open(fileName, "r")
fOut = open("output.json", "w")

cont = 1
mLen = 0 
nLen = 0
mData = []
mStr = '{'
nStr = '{'

for l in fIn:
  if cont == 1:
    nLen = l
    cont+=1
    continue
  data = l.split()
  mData.append('"{}": {{"from": {},"to": {},"id": "{}","color": {{ }},"label": "{:.2f}"}}'.format(str(mLen+1), data[0], data[1], str(mLen+1), float(data[2])))
  mLen += 1
  
for i in mData:
  if mData.index(i) == len(mData)-1:
    mStr += i + "}"
    break
  mStr += i + ","
  
for i in range(int(nLen)):
  nStr += '"{}": {{"id": {},"label": "{}"}}'.format(i+1, i+1, i+1)
  if i == int(nLen) - 1:
    nStr += '}'
  else:
    nStr += ','

fOut.write(
'{"data": '+
'{"nodes": {"_subscribers": {"*": [],"add": [{ }],"remove": [{ }],"update": [{ }]},"_options": { },'+
'"_data": {},"length": {},'.format(nStr, nLen)+
'"_idProp": "id","_type": { }},'+
'"edges": {"_subscribers": {"*": [],"add": [{ }],"remove": [{ }],"update": [{ }]},"_options": { },'+
'"_data": {},"length": {},'.format(mStr, mLen)+
'"_idProp": "id","_type": { }}},"options": {"locale": "pt-br","manipulation": {"enabled": false},"edges": {"font": {"color": "#ffffff","strokeWidth": 0,"size": 18 } },"nodes": {"color": {"border": "#698B69","background": "#458B74","highlight": {"border": "#698B69","background": "#4f6e4f"}},"font": {"color": "white"}},"physics": {"enabled": true,"forceAtlas2Based": {"gravitationalConstant": -50,"centralGravity": 0.01,"springConstant": 0.02,"springLength": 100,"damping": 0.4,"avoidOverlap": 0},"maxVelocity": 50,"minVelocity": 0.1,"solver": "forceAtlas2Based","stabilization": {"enabled": true,"iterations": 1000,"updateInterval": 100,"onlyDynamicEdges": false,"fit": true},"timestep": 0.5,"adaptiveTimestep": true } },"ponderado": true,"ordenado": false}')

print("\n Arquivo de saída gerado.\n>> output.json")