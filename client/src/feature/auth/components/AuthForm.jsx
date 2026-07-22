import { fieldRegistry } from "../config/fieldRegistry"

export default function AuthForm({
	inputs,
	formData,
	handleChange,
	extraProps = {},
}) {
	return (
		<>
			{inputs.map((field) => {
				const Field = fieldRegistry[field.type]
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
