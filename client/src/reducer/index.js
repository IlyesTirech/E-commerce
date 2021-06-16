import {combineReducers} from 'redux'
import auth from './auth';
import message from './message';
import productReducer from "./productReducer"

const rootReducer = combineReducers({
    product: productReducer,
    auth: auth,
    message: message,
})

export default rootReducer;