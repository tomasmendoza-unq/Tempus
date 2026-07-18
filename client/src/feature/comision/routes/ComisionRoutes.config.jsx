import { Route } from "react-router-dom"
import { comisionRoutes } from "./comisionRoutes"

export const ComisionRoutesConfig = (
	<Route path="/refactor">
		{comisionRoutes.map((route) => (
			<Route
				key={route.path}
				index={route.path === "/"}
				path={route.path === "/" ? undefined : route.path}
				element={route.element}
			/>
		))}
	</Route>
)
