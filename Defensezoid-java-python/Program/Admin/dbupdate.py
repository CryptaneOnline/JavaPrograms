from azure.cosmos import exceptions, CosmosClient, PartitionKey

import uuid
import sys

nm=''
imei=''
srl=''
argum=['','','','']
if __name__ == "__main__":
    for i, arg in enumerate(sys.argv):
        argum[i]=arg
    nm=argum[1]
    imei=argum[2]
    srl=argum[3]

endpoint = "https://aditya-db.documents.azure.com:443/"
key = '<AUTHTOKEN>'

client = CosmosClient(endpoint, key)
database_name = 'AuthorizedDatabase'
database = client.create_database_if_not_exists(id=database_name)
container_name = 'AuthUserList'
container = database.create_container_if_not_exists(
    id=container_name,
    partition_key=PartitionKey(path="/Name"),
    offer_throughput=400
)
user_items_to_create = [{
        'Name':nm,
        'IMEI':imei,
        'Serial':srl,
        'id': imei
        }]
for user_item in user_items_to_create:
    container.create_item(body=user_item)
print(1)
