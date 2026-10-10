
import './App.css'

const metrics = [
  { title: 'Capacity', value: '85.4 ML', icon: 'database', color: 'blue' },
  { title: 'Available', value: '62.8 ML', icon: 'droplet', color: 'green' },
  { title: 'Consumed', value: '22.6 ML', icon: 'activity', color: 'orange' },
  { title: 'Alerts', value: '4', icon: 'alert', color: 'red' },
]

const zones = [
  { name: 'Zone A', status: 'Normal', level: 82 },
  { name: 'Zone B', status: 'Low', level: 28 },
]

const resources = [
  { name: 'Lake Reservoir A', level: 82, amount: '24.6 ML', color: 'blue' },
  { name: 'Main Water Tank', level: 58, amount: '18.2 ML', color: 'green' },
]

const alerts = [
  { name: 'Low water level', location: 'Supply Zone B', type: 'warning' },
  { name: 'Pipeline leakage', location: 'North Zone', type: 'danger' },
]

function App() {
  return (
    <div className="dashboard">
      <aside className="sidebar">
        <div className="brand">
          <div className="brand-logo">≈</div>
          <span>AquaWatch</span>
        </div>

        <div className="nav-heading">DASHBOARD</div>

        <nav className="navigation">
          <a className="nav-link active" href="#overview">
            <span>▦</span> Overview
          </a>
          <a className="nav-link" href="#resources">
            <span>◈</span> Water Sources
          </a>
          <a className="nav-link" href="#distribution">
            <span>⇄</span> Distribution
          </a>
          <a className="nav-link" href="#quality">
            <span>✧</span> Water Quality
          </a>
          <a className="nav-link" href="#maintenance">
            <span>⚙</span> Maintenance
          </a>
          <a className="nav-link" href="#complaints">
            <span>☏</span> Complaints
          </a>
        </nav>

        <div className="sidebar-footer">
          <span className="online-dot" />
          System operational
        </div>
      </aside>

      <main className="main-content" id="overview">
        <header className="topbar">
          <div className="breadcrumb">
            Dashboard <span>/</span> Overview
          </div>
          <div className="user-actions">
            <button className="notification-button" aria-label="Notifications">
              ♧ <span className="notification-dot" />
            </button>
            <div className="admin-profile">
              <div className="avatar">AD</div>
              <span>Admin</span>
              <span className="chevron">⌄</span>
            </div>
          </div>
        </header>

        <section className="page-heading">
          <div>
            <div className="eyebrow">RESOURCE MANAGEMENT</div>
            <h1>Water Resource Overview</h1>
            <p>Monitor. Manage. Conserve.</p>
          </div>
          <span className="live-status">
            <span className="online-dot" /> Monitoring
          </span>
        </section>

        <section className="metrics-grid">
          {metrics.map((metric) => (
            <article className="metric-card" key={metric.title}>
              <div className="metric-top">
                <span>{metric.title}</span>
                <div className={`metric-icon ${metric.color}`}>
                  {metric.icon === 'database' && '▤'}
                  {metric.icon === 'droplet' && '♧'}
                  {metric.icon === 'activity' && '↗'}
                  {metric.icon === 'alert' && '!'}
                </div>
              </div>
              <h2>{metric.value}</h2>
            </article>
          ))}
        </section>

        <section className="content-grid">
          <article className="panel availability-panel">
            <div className="panel-heading">
              <div>
                <h3>Water Availability</h3>
                <p>Water levels over the last 7 days</p>
              </div>
              <span className="chart-label">Last 7 days ⌄</span>
            </div>

            <div className="chart-legend">
              <span><i className="blue-key" /> Available</span>
              <span><i className="gray-key" /> Consumed</span>
            </div>

            <div className="chart-container">
              <div className="y-axis">
                <span>80 ML</span>
                <span>60 ML</span>
                <span>40 ML</span>
                <span>20 ML</span>
                <span>0 ML</span>
              </div>

              <div className="chart-plot">
                {[0, 1, 2, 3, 4].map((line) => (
                  <div className="chart-gridline" key={line} style={{ top: `${line * 25}%` }} />
                ))}

                <svg
                  className="chart-svg"
                  viewBox="0 0 500 170"
                  preserveAspectRatio="none"
                  role="img"
                  aria-label="Illustrative water availability line chart"
                >
                  <defs>
                    <linearGradient id="areaFill" x1="0" y1="0" x2="0" y2="1">
                      <stop offset="0%" stopColor="#3984e9" stopOpacity=".22" />
                      <stop offset="100%" stopColor="#3984e9" stopOpacity="0" />
                    </linearGradient>
                  </defs>

                  <path
                    d="M0 45 L83 60 L166 34 L250 68 L333 53 L416 88 L500 74 L500 170 L0 170 Z"
                    fill="url(#areaFill)"
                  />
                  <polyline
                    points="0,45 83,60 166,34 250,68 333,53 416,88 500,74"
                    fill="none"
                    stroke="#3984e9"
                    strokeWidth="3"
                    vectorEffect="non-scaling-stroke"
                  />
                  <polyline
                    points="0,132 83,123 166,138 250,116 333,128 416,109 500,120"
                    fill="none"
                    stroke="#9caabd"
                    strokeWidth="2"
                    strokeDasharray="5 5"
                    vectorEffect="non-scaling-stroke"
                  />
                </svg>

                <div className="x-axis">
                  <span>Mon</span><span>Tue</span><span>Wed</span>
                  <span>Thu</span><span>Fri</span><span>Sat</span><span>Sun</span>
                </div>
              </div>
            </div>
          </article>

          <article className="panel zone-panel" id="distribution">
            <div className="panel-heading">
              <div>
                <h3>Supply Zone Status</h3>
                <p>Current availability by zone</p>
              </div>
              <a href="#resources" className="view-link">View all →</a>
            </div>

            <div className="zone-table-header">
              <span>ZONE</span><span>LEVEL</span><span>STATUS</span>
            </div>

            {zones.map((zone) => (
              <div className="zone-row" key={zone.name}>
                <strong>{zone.name}</strong>
                <div className="zone-progress">
                  <span
                    className={zone.status === 'Low' ? 'low-level' : ''}
                    style={{ width: `${zone.level}%` }}
                  />
                </div>
                <span className={`zone-status ${zone.status.toLowerCase()}`}>
                  <i /> {zone.status}
                </span>
              </div>
            ))}

            <div className="zone-notice">
              <span className="notice-icon">!</span>
              <div>
                <strong>Zone B requires attention</strong>
                <p>Water availability is below the recommended level.</p>
              </div>
            </div>
          </article>
        </section>

        <section className="content-grid bottom-grid">
          <article className="panel resources-panel" id="resources">
            <div className="panel-heading">
              <div>
                <h3>Water Resources</h3>
                <p>Reservoir levels and capacity</p>
              </div>
              <a href="#resources" className="view-link">View all →</a>
            </div>

            {resources.map((resource) => (
              <div className="resource-row" key={resource.name}>
                <div className={`resource-icon ${resource.color}`}>≈</div>
                <div className="resource-details">
                  <div className="resource-name">{resource.name}</div>
                  <div className="resource-progress">
                    <span
                      className={resource.color}
                      style={{ width: `${resource.level}%` }}
                    />
                  </div>
                </div>
                <div className="resource-value">
                  <strong>{resource.amount}</strong>
                  <span>{resource.level}% full</span>
                </div>
              </div>
            ))}
          </article>

          <article className="panel alerts-panel">
            <div className="panel-heading">
              <div>
                <h3>Recent Alerts</h3>
                <p>Issues requiring attention</p>
              </div>
              <span className="alert-count">2 alerts</span>
            </div>

            {alerts.map((alert) => (
              <div className="alert-row" key={alert.name}>
                <div className={`alert-icon ${alert.type}`}>
                  {alert.type === 'warning' ? '!' : '⌁'}
                </div>
                <div className="alert-details">
                  <strong>{alert.name}</strong>
                  <span>{alert.location}</span>
                </div>
                <span className="alert-arrow">›</span>
              </div>
            ))}

            <button
              className="all-alerts-button"
              onClick={() => window.alert('Alerts module will be connected to Spring Boot later.')}
            >
              View all alerts →
            </button>
          </article>
        </section>

        <footer className="footer">
          <span>AquaWatch · Integrated Water &amp; Natural Resource Management</span>
          <span>Dashboard preview · Sample data</span>
        </footer>
      </main>
    </div>
  )
}

export default App