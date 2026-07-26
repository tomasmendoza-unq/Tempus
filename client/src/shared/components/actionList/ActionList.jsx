import { HeaderCursadas } from "../../../components/Perfil/HeaderCursadas"

export const ActionList = ({
	items,
	headerTitle,
	headerAction,
	emptyMessage,
	renderItem,
}) => (
	<section>
		<HeaderCursadas title={headerTitle}>{headerAction}</HeaderCursadas>

		{items.length ? (
			items.map(renderItem)
		) : (
			<div className="py-10 text-center text-gray-400 italic text-sm">
				{emptyMessage}
			</div>
		)}
	</section>
)
