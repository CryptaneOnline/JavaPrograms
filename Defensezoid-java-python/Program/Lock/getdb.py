from azure.cosmos import CosmosClient
import os

url = 'https://aditya-db.documents.azure.com:443/'
key = '<AUTHTOKEN>'
client = CosmosClient(url, credential=key)
database_name = 'AuthorizedDatabase'
database = client.get_database_client(database_name)
container_name = 'AuthUserList'
container = database.get_container_client(container_name)

# Enumerate the returned items
import json
import os
s=''
for item in container.query_items(
        query='SELECT * FROM mycontainer',
        enable_cross_partition_query=True):
            print(json.dumps(item, indent=True))
