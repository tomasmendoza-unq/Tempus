import { ReactFlow, ReactFlowProvider } from "@xyflow/react"
import "@xyflow/react/dist/style.css"
import "./NodoDeMaterias.css"

export default function NodoDeMaterias({ materia, onClick }) {
  const materiaNodo = {
    id: `${materia.materiaId}`,
    position: { x: 0, y: 0 },
    data: { label: `${materia.materiaNombre}` },
    className: "nodo-materia",
  }

  const correlativas = materia.correlativas || []
  correlativas.forEach((correlativa, index) => {
    const nodoCorrelativa = {
      id: `correlativa-${index}`,
      position: { x: 200 * (index + 1), y: 100 },
      data: { label: correlativa.materiaNombre },
    }
    materiaNodo[`correlativa-${index}`] = nodoCorrelativa
    materiaNodo.edges = materiaNodo.edges || []
    materiaNodo.edges.push({
      id: `edge-${materia.materiaId}-to-correlativa-${index}`,
      source: materia.materiaId.toString(),
      target: nodoCorrelativa.id,
      animated: true,
    })
  })

  return (
    <div className="w-64 h-40 flex items-center justify-center">
      <ReactFlowProvider>
        <ReactFlow
          colorMode="dark"
          nodes={[materiaNodo]}
          edges={materiaNodo.edges}
          fitView
          panOnScroll={false}
          zoomOnScroll={false}
          panOnDrag={false}
          nodesDraggable={false}
          nodesConnectable={false}
          onNodeClick={() => {
            onClick()
          }}
        />
      </ReactFlowProvider>
    </div>
  )
}

/*
<ReactFlow
  colorMode="dark"  // o "light"
  nodes={[materiaNodo]}
  edges={materiaNodo.edges}
  fitView
  
  // Zoom
  minZoom={0.5}
  maxZoom={2}
  defaultZoom={1}
  
  // Interactividad
  nodesDraggable={true}  // Permitir arrastrar nodos
  nodesConnectable={true}  // Permitir conectar nodos
  elementsSelectable={true}  // Permitir seleccionar elementos
  
  // Navegación
  panOnScroll={true}  // Moverse con scroll
  zoomOnScroll={true}  // Zoom con scroll
  panOnDrag={true}  // Moverse al arrastrar fondo
  
  // Posición inicial
  defaultViewport={{ x: 0, y: 0, zoom: 1 }}
  
  // Atribución
  attributionPosition="bottom-left"
/>
*/
