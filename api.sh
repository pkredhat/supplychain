#!/usr/bin/env bash

pretty() {
  python3 -m json.tool
}

extract_id() {
  pretty | grep id | cut -d':' -f2 | grep -oP "[a-z0-9]*"
}





# -------------------Products -------------------

post_product() {
  curl -sX POST \
    'http://localhost:8080/api/product' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
      "name": "'"${1}"'",
      "headquarters": "'"${2}"'",
      "created": "'"${3}"'"
  }'
}

get_product_by_id() {
  curl -s 'http://localhost:8080/api/product/id/'"${1}"
}

get_product_by_name() {
  curl -s 'http://localhost:8080/api/product/name/'"${1}"
}

get_products() {
  curl -s 'http://localhost:8080/api/products'
}

delete_products() {
  curl -sX DELETE 'http://localhost:8080/api/products'
}

# echo "DELETE products"
# delete_products

echo -e "\n\nPOST Product 'DewaltHammer'"
mdb_id=$(post_product "Dewalt" "Hammer" "2009-02-11" | extract_id)

echo "POST Product 'Kobalt Nails'"
post_product "Kobalt" "Nails" "1998-09-04" > /dev/null

# echo -e "\nGET Product 'Dewalt Hammer' by 'id'"
# get_product_by_id "${mdb_id}" | pretty

# echo -e "\nGET Product 'Kobalt' by 'name'"
# get_product_by_name "Kobalt" | pretty

# echo -e "\nGET Products"
# get_products | pretty

# ------------------- END Products -------------------------




# -------------------Companies ------------------------

post_company() {
  curl -sX POST \
    'http://localhost:8080/api/company' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
      "name": "'"${1}"'",
      "headquarters": "'"${2}"'",
      "created": "'"${3}"'"
  }'
}


get_company_by_id() {
  curl -s 'http://localhost:8080/api/company/id/'"${1}"
}

get_company_by_name() {
  curl -s 'http://localhost:8080/api/company/name/'"${1}"
}

get_companies() {
  curl -s 'http://localhost:8080/api/companies'
}

delete_companies() {
  curl -sX DELETE 'http://localhost:8080/api/companies'
}

# echo "DELETE Companies"
# delete_companies


# echo -e "\n\nPOST Company 'MongoDB'"
# mdb_id=$(post_company "MongoDB" "New York" "2009-02-11" | extract_id)
# echo "POST Company 'Google'"
# post_company "Google" "Mountain View" "1998-09-04" > /dev/null

# echo -e "\nGET Company 'MongoDB' by 'id'"
# get_company_by_id "${mdb_id}" | pretty

# echo -e "\nGET Company 'Google' by 'name'"
# get_company_by_name "Google" | pretty

# echo -e "\nGET Companies"
# get_companies | pretty


# ------------------- Employees ------------------------


post_employee() {
  curl -sX POST \
    'http://localhost:8080/api/employee' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
      "firstName": "'"${1}"'",
      "lastName": "'"${2}"'",
      "company": "'"${3}"'",
      "joined": "'"${4}"'",
      "salary": "'"${5}"'"
  }'
}

get_employee_by_id() {
  curl -s 'http://localhost:8080/api/employee/id/'"${1}"
}

get_employees() {
  curl -s 'http://localhost:8080/api/employees'
}

delete_employees() {
  curl -sX DELETE 'http://localhost:8080/api/employees'
}


# echo -e "\nDELETE Employees"
# delete_employees

# echo -e "\nPOST Employee Maxime"
# maxime_id=$(post_employee "Maxime" "Beugnet" "Google" "2018-02-12" "2468" | extract_id)
# echo "POST Employee Tim"
# tim_id=$(post_employee "Tim" "Kelly" "MongoDB" "2023-08-23" "13579" | extract_id)

# echo -e "\nGET Employee 'Maxime' by 'id'"
# get_employee_by_id "${maxime_id}" | pretty
# echo -e "\nGET Employee 'Tim' by 'id'"
# get_employee_by_id "${tim_id}" | pretty

# echo -e "\nGET Employees"
# get_employees | pretty

# ------------------- END Employees --------------------