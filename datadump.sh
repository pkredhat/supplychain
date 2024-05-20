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

post_customer() {
  curl -sX POST \
    'http://localhost:8080/api/customer' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
      "firstName": "'"${1}"'",
      "lastName": "'"${2}"'",
      "address": "'"${3}"'",
      "phoneNumber": "'"${4}"'",
      "created": "'"${5}"'"
  }'
}

post_order() {
  curl -sX POST \
    'http://localhost:8080/api/order' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
      "orderName": "'"${1}"'",
      "customerId": "'"${2}"'",
      "productId": "'"${3}"'",
      "orderTotal": "'"${4}"'"
  }'
}

mdb_id=$(post_product "Fridge" "LG" "2010-02-11" | extract_id)
mdb_id=$(post_product "TV" "Panasonic" "2008-09-11" | extract_id)
mdb_id=$(post_product "Dining Table" "Full length" "2009-08-19" | extract_id)
mdb_id=$(post_product "Couch" "Italian Suede" "2009-12-12" | extract_id)
mdb_id=$(post_product "Heater" "Electric Radial" "2020-06-14" | extract_id)
mdb_id=$(post_product "Bed" "Sealy Queen" "2021-07-07" | extract_id)
mdb_id=$(post_product "Cupboard" "Mahogany Oak" "2022-04-05" | extract_id)
mdb_id=$(post_product "Pillows" "Sleep Comfort" "2020-22-09" | extract_id)
mdb_id=$(post_product "Cofee Table" "Brown coffee table" "2001-22-05" | extract_id)
mdb_id=$(post_product "Drill" "Dewalt Hammer Drill" "2024-28-03" | extract_id)
mdb_id=$(post_product "Tennis Racquet" "Wilson Pro series" "1996-07-11" | extract_id)




mdb_id=$(post_customer "Cecilia" "Chapman" "711-2880 Nulla St.Mankato Mississippi 96522" "(257) 563-7401" "2024-05-18" | extract_id)
mdb_id=$(post_customer "Iris" "Watson" "P.O. Box 283 8562 Fusce Rd.Frederick Nebraska 20620" "(257) (372) 587-2335" "2024-05-18" | extract_id)
mdb_id=$(post_customer "Celeste" "Slater" "606-3727 Ullamcorper. StreetRoseville NH 11523" "(786) 713-8616" "2024-05-18" | extract_id)
mdb_id=$(post_customer "Theodore" "Lowe" "Ap #867-859 Sit Rd.Azusa New York 39531" "(793) 151-6230" "2024-05-18" | extract_id)
mdb_id=$(post_customer "Calista" "Wise" "7292 Dictum Av.San Antonio MI 47096" "(492) 709-6392" "2024-05-18" | extract_id)
mdb_id=$(post_customer "Kyla" "Olsen" "Ap #651-8679 Sodales Av.Tamuning PA 10855" "(654) 393-5734" "2024-05-18" | extract_id)
mdb_id=$(post_customer "Forrest" "Ray" "191-103 Integer Rd. Corona New Mexico 08219" "((404) 960-3807" "2024-05-18" | extract_id)
mdb_id=$(post_customer "Hiroko" "Potter" "P.O. Box 887 2508 Dolor. Av.Muskegon KY 12482" "(314) 244-6306" "2024-05-18" | extract_id)
mdb_id=$(post_customer "Nyssa" "Vasquez" "511-5762 At Rd. Chelsea MI 67708" "(947) 278-5929" "2024-05-18" | extract_id)
mdb_id=$(post_customer "Lawrence" "Moreno" "935-9940 Tortor. Street Santa Rosa MN 98804" "(684) 579-1879" "2024-05-18" | extract_id)
mdb_id=$(post_customer "Ina" "Moran" "P.O. Box 929 4189 Nunc Road Lebanon KY 69409" "(389) 737-2852" "2024-05-18" | extract_id)


mdb_id=$(post_order "Order #21132" "3" "6" "59.99" "2024-05-18" | extract_id)
mdb_id=$(post_order "Order #21133" "6" "5" "59.99" "2024-05-18" | extract_id)
mdb_id=$(post_order "Order #21134" "5" "2" "59.99" "2024-05-18" | extract_id)
mdb_id=$(post_order "Order #21135" "3" "4" "59.99" "2024-05-18" | extract_id)
mdb_id=$(post_order "Order #21136" "8" "7" "59.99" "2024-05-18" | extract_id)
mdb_id=$(post_order "Order #21137" "9" "8" "59.99" "2024-05-18" | extract_id)
mdb_id=$(post_order "Order #21138" "2" "9" "59.99" "2024-05-18" | extract_id)
mdb_id=$(post_order "Order #21139" "4" "2" "59.99" "2024-05-18" | extract_id)
mdb_id=$(post_order "Order #21140" "5" "1" "59.99" "2024-05-18" | extract_id)
mdb_id=$(post_order "Order #21141" "6" "3" "59.99" "2024-05-18" | extract_id)
mdb_id=$(post_order "Order #21142" "5" "4" "59.99" "2024-05-18" | extract_id)