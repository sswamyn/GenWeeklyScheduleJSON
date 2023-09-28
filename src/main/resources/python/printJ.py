import pandas as pd
import json

# read json file
#df = pd.read_json (r'sample.json')
df = pd.read_json (r'default1.json')

json_formatted_str = df.to_json

json_formatted_str2 = json.dumps(json_formatted_str, indent=2)
# print dataframe
print (json_formatted_str2)