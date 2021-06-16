const initState = {
    products: {}
}

const productReducer = (state=initState, action) => {
    switch(action.type){
        case "FETCH_PRODUCTS":
            return {...state,
            products: action.payload.products,
            }
        default:
            return {...state}
    }
}

export default productReducer