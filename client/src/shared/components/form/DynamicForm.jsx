import { field as fields } from "./config/field"

export default function DynamicForm({
	inputs,
	formData,
	handleChange,
	extraProps = {},
}) {
	return (
		<>
			{inputs.map((field) => {
				const Field = fields[field.type]
				return (
					<Field
						key={field.name}
						{...field}
						value={formData[field.name]}
						onChange={(eOrValue) => handleChange(eOrValue, field.name)}
						{...extraProps[field.name]}
					/>
				)
			})}
		</>
	)
}
