export const SelectCarrera = ({ carreras, onChange, value }) => {
	return (
		<select
			id="carrera"
			name="carreraId"
			value={value}
			onChange={onChange}
			className="border border-gray-300 rounded-md p-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
		>
			<option value="">Seleccione una carrera</option>
			{carreras.map((carrera) => (
				<option key={carrera.idCarrera} value={carrera.idCarrera}>
					{carrera.nombre}
				</option>
			))}
		</select>
	)
}
