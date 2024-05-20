#!/usr/bin/env bash

pretty() {
  python3 -m json.tool
}

extract_id() {
  pretty | grep id | cut -d':' -f2 | grep -oP "[a-z0-9]*"
}

post_product() {
  curl -sX POST \
    'http://localhost:8080/api/product' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
      "productName": "'"${1}"'",
      "productDescription": "'"${2}"'",
      "created": "'"${3}"'"
  }'
}



mdb_id=$(post_product "Fridge" "LG" "2010-02-11" | extract_id)
mdb_id=$(post_product "TV" "Panasonic" "2008-02-11" | extract_id)
mdb_id=$(post_product "Dining Table" "Full length" "2009-02-11" | extract_id)
mdb_id=$(post_product "Couch" "Italian Suede" "2009-02-11" | extract_id)
mdb_id=$(post_product "Heater" "Electric Radial" "2020-02-11" | extract_id)
mdb_id=$(post_product "Bed" "Sealy Queen" "2021-02-11" | extract_id)
mdb_id=$(post_product "Cupboard" "Mahogany Oak" "2022-02-11" | extract_id)
mdb_id=$(post_product "Pillows" "Sleep Comfort" "2020-02-11" | extract_id)
mdb_id=$(post_product "Cofee Table" "Brown coffee table" "2001-02-11" | extract_id)
mdb_id=$(post_product "Drill" "Dewalt Hammer Drill" "2024-02-11" | extract_id)