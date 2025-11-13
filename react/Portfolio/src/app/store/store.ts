import { useDispatch, useSelector } from "react-redux";
import { configureStore } from "@reduxjs/toolkit"
import fontsReducer from "../fontsSlice";

export const store = configureStore({
    reducer: {
        fonts: fontsReducer
    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(
            //todo
        )
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch

export const useAppDispatch = useDispatch.withTypes<AppDispatch>(); //permet d'utiliser dispatch pour altérer l'état du store comme changer un counter ou passer en darkMode
export const useAppSelector = useSelector.withTypes<RootState>(); // permet de lire l'état du store et ainsi de récupérer la valeur par exemple d'un compteur ou si on est en dark mode

