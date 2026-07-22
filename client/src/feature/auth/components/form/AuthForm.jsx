import { field as fieldConfig } from "../../config/field"

export default function AuthForm({
	inputs,
	formData,
	handleChange,
	extraProps = {},
}) {
	return (
		<>
			{inputs.map((field) => {
				const Field = fieldConfig[field.type]
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
