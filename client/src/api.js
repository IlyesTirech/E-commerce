import axios from "axios";

const BASE_URL_PRODUCT = "http://localhost:8080/api/product"

//CREATE PRODUCTS
const CREATE_PRODUCTS = `/create`
//GET PRODUCT
const GET_PRODUCTS = `/getAll`

//Create Products
export const createProductsURL = () => `${BASE_URL_PRODUCT}${CREATE_PRODUCTS}`
//Add images to products
export const addImageToProductURL = (product_id) => `${BASE_URL_PRODUCT}/${product_id}/image/upload`
//Get All Products
export const getAllProductsURL = () => `${BASE_URL_PRODUCT}${GET_PRODUCTS}`
//Get One Product
export const getOneProductURL = (product_id) => `${BASE_URL_PRODUCT}/${product_id}`
