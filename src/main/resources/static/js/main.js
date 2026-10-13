/* ================================================================
   PARTICLES
   ================================================================ */
(function() {
  const canvas = document.getElementById('particle-canvas');
  if (!canvas) return;
  const ctx    = canvas.getContext('2d');
  let W, H, particles = [];

  function resize() {
    W = canvas.width  = window.innerWidth;
    H = canvas.height = window.innerHeight;
  }
  resize();
  window.addEventListener('resize', resize);

  class Particle {
    constructor() { this.reset(); }
    reset() {
      this.x  = Math.random() * W;
      this.y  = Math.random() * H;
      this.r  = Math.random() * 1.4 + .3;
      this.vx = (Math.random() - .5) * .4;
      this.vy = (Math.random() - .5) * .4;
      this.a  = Math.random() * .5 + .1;
      this.color = Math.random() > .5 ? '123,47,255' : '0,229,255';
    }
    draw() {
      ctx.beginPath();
      ctx.arc(this.x, this.y, this.r, 0, Math.PI * 2);
      ctx.fillStyle = `rgba(${this.color},${this.a})`;
      ctx.fill();
    }
    update() {
      this.x += this.vx;
      this.y += this.vy;
      if (this.x < 0 || this.x > W || this.y < 0 || this.y > H) this.reset();
    }
  }

  for (let i = 0; i < 120; i++) particles.push(new Particle());

  function connect() {
    for (let i = 0; i < particles.length; i++) {
      for (let j = i + 1; j < particles.length; j++) {
        const dx = particles[i].x - particles[j].x;
        const dy = particles[i].y - particles[j].y;
        const d  = Math.sqrt(dx*dx + dy*dy);
        if (d < 130) {
          ctx.beginPath();
          ctx.moveTo(particles[i].x, particles[i].y);
          ctx.lineTo(particles[j].x, particles[j].y);
          ctx.strokeStyle = `rgba(123,47,255,${.08 * (1 - d/130)})`;
          ctx.lineWidth = .5;
          ctx.stroke();
        }
      }
    }
  }

  function loop() {
    ctx.clearRect(0, 0, W, H);
    particles.forEach(p => { p.update(); p.draw(); });
    connect();
    requestAnimationFrame(loop);
  }
  loop();
})();

/* ================================================================
   CUSTOM CURSOR
   ================================================================ */
(function() {
  const dot  = document.getElementById('cursor-dot');
  const ring = document.getElementById('cursor-ring');
  if (!dot || !ring) return;
  let mx = 0, my = 0, rx = 0, ry = 0;

  document.addEventListener('mousemove', e => {
    mx = e.clientX; my = e.clientY;
    dot.style.left = mx + 'px';
    dot.style.top  = my + 'px';
  });

  (function track() {
    rx += (mx - rx) * .12;
    ry += (my - ry) * .12;
    ring.style.left = rx + 'px';
    ring.style.top  = ry + 'px';
    requestAnimationFrame(track);
  })();

  document.querySelectorAll('a,button,.pcard,.skill-row,.exp-card,.cinfo-card').forEach(el => {
    el.addEventListener('mouseenter', () => document.body.classList.add('cursor-hover'));
    el.addEventListener('mouseleave', () => document.body.classList.remove('cursor-hover'));
  });
})();

/* ================================================================
   NAVBAR
   ================================================================ */
(function() {
  const nav = document.getElementById('navbar');
  const links = document.querySelectorAll('.nav-link');
  const sections = document.querySelectorAll('section[id]');
  if (!nav) return;

  window.addEventListener('scroll', () => {
    nav.classList.toggle('scrolled', window.scrollY > 30);
    let cur = '';
    sections.forEach(s => { if (window.scrollY >= s.offsetTop - 120) cur = s.id; });
    links.forEach(l => l.classList.toggle('active', l.getAttribute('href') === '#' + cur));
  }, { passive: true });

  const hb = document.getElementById('hamburger');
  const nl = document.getElementById('nav-links');
  if (hb && nl) {
    hb.addEventListener('click', () => {
      nl.classList.toggle('open');
      hb.classList.toggle('open');
    });
    nl.querySelectorAll('.nav-link').forEach(l => l.addEventListener('click', () => {
      nl.classList.remove('open'); hb.classList.remove('open');
    }));
  }
})();

/* ================================================================
   TYPED TEXT
   ================================================================ */
(function() {
  const el     = document.getElementById('typed');
  if (!el) return;
  const phrases = ['scalable APIs', 'Object-Oriented code', 'real-time systems', 'cloud-native backends', 'robust apps'];
  let pi = 0, ci = 0, del = false;
  const TYPE_SPD = 90, DEL_SPD = 45, PAUSE = 2000;

  function tick() {
    const p = phrases[pi];
    if (del) {
      el.textContent = p.slice(0, --ci);
      if (ci === 0) { del = false; pi = (pi + 1) % phrases.length; return setTimeout(tick, 400); }
    } else {
      el.textContent = p.slice(0, ++ci);
      if (ci === p.length) { del = true; return setTimeout(tick, PAUSE); }
    }
    setTimeout(tick, del ? DEL_SPD : TYPE_SPD);
  }
  setTimeout(tick, 1200);
})();

/* ================================================================
   STAT COUNTERS
   ================================================================ */
(function() {
  const stats = document.querySelectorAll('.stat-val[data-to]');
  let done = false;

  function run() {
    if (done) return;
    const hero = document.querySelector('.hero-stats');
    if (!hero) return;
    if (hero.getBoundingClientRect().top < window.innerHeight) {
      done = true;
      stats.forEach(el => {
        const target = +el.dataset.to;
        let v = 0;
        const step = Math.ceil(target / (1800 / 16));
        const t = setInterval(() => {
          v = Math.min(v + step, target);
          el.textContent = v;
          if (v >= target) clearInterval(t);
        }, 16);
      });
    }
  }
  window.addEventListener('scroll', run, { passive: true });
  run();
})();

/* ================================================================
   SCROLL REVEAL (Intersection Observer)
   ================================================================ */
(function() {
  const io = new IntersectionObserver(entries => {
    entries.forEach((e, i) => {
      if (e.isIntersecting) {
        setTimeout(() => e.target.classList.add('up'), i * 40);
        io.unobserve(e.target);
      }
    });
  }, { threshold: 0.1, rootMargin: '0px 0px -50px 0px' });

  document.querySelectorAll('.reveal').forEach(el => io.observe(el));
})();

/* ================================================================
   SKILL BAR ANIMATION
   ================================================================ */
(function() {
  const io = new IntersectionObserver(entries => {
    entries.forEach(e => {
      if (e.isIntersecting) {
        const fill = e.target.querySelector('.bar-fill');
        if (fill) fill.style.width = e.target.dataset.pct + '%';
        io.unobserve(e.target);
      }
    });
  }, { threshold: 0.3 });
  document.querySelectorAll('.skill-row').forEach(r => io.observe(r));
})();

/* ================================================================
   3D CARD TILT
   ================================================================ */
document.querySelectorAll('.pcard').forEach(card => {
  card.addEventListener('mousemove', e => {
    const r   = card.getBoundingClientRect();
    const x   = e.clientX - r.left - r.width  / 2;
    const y   = e.clientY - r.top  - r.height / 2;
    const rx  = (y / r.height) * 10;
    const ry  = (x / r.width)  * -10;
    card.style.transform = `perspective(800px) rotateX(${rx}deg) rotateY(${ry}deg) scale(1.03)`;
  });
  card.addEventListener('mouseleave', () => {
    card.style.transform = '';
  });
});

/* ================================================================
   PROJECT FILTER
   ================================================================ */
(function() {
  const btns  = document.querySelectorAll('.f-btn');
  const cards = document.querySelectorAll('.pcard');

  btns.forEach(btn => {
    btn.addEventListener('click', () => {
      btns.forEach(b => b.classList.remove('on'));
      btn.classList.add('on');
      const f = btn.dataset.f;
      cards.forEach(c => {
        const match = f === 'all' || c.dataset.cat === f;
        c.classList.toggle('gone', !match);
        if (match) c.style.animation = 'fadeUp .4s ease forwards';
      });
    });
  });
})();

/* ================================================================
   CONTACT FORM SUBMITTING EFFECT
   ================================================================ */
(function() {
  const form  = document.getElementById('contact-form');
  const btnTxt = document.getElementById('fsend-text');
  if (form && btnTxt) {
    form.addEventListener('submit', () => {
      btnTxt.textContent = 'Sending...';
    });
  }
})();

/* ================================================================
   BACK TO TOP + MISC
   ================================================================ */
const yr = document.getElementById('yr');
if (yr) yr.textContent = new Date().getFullYear();

const btt = document.getElementById('btt');
if (btt) {
  window.addEventListener('scroll', () => {
    btt.style.opacity = window.scrollY > 500 ? '1' : '0';
  }, { passive: true });
  btt.style.opacity = '0';
  btt.style.transition = 'opacity .3s';
}
