import axios from 'axios'
import { getAllProductsURL } from '../api'
import authHeader from '../services/auth-header';

export const loadProducts = () => async (dispatch) => {

    const productData = await axios.get(getAllProductsURL(), 
    {headers:authHeader()});
    
    dispatch({
        type: "FETCH_PRODUCTS",
        payload: {
            products: productData.data,
        }
    })
}