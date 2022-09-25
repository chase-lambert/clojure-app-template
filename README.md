# clojure-app-template
## Full stack Clojure/Clojurescript app
  ### backend: deps.edn, jetty, reitit, aero (for config)
  
  I've commented out some dependencies in `deps.edn`, mostly database stuff. You can just delete that if not needed.
  
  ### frontend: shadow-cljs, reagent, tailwindcss (w/ daisy ui)
  
  I've commented out re-frame for now but it will probably be added back in when needed.
  
  ### deployed through dockerfile 

Demo is currently deployed and running live through `render.com` at: https://clojure-demo-app.onrender.com/ 

It's using the free tier so first load might take a while, but when live I'm getting lighthouse scores of 97-99% Performance 
(I only get 89% here running the production release on localhost so `render.com` must be doing something nice here) 
and 100% on Accessibility, Best Practices, and SEO (but the demo is only displaying Hello World). 
The free tier allows for 512mb and this demo seems to use up to ~170mb for just the "Hello World" according to `render.com`'s metrics but I haven't looked into too much yet.

The dockerfile should allow it to be used elsewhere like `fly.io`, `railway.app`, and `heroku` but I haven't tested.

** I don't know how to actually create an official template yet so I manually have to change every occurrence of `TODO` in the repo 
to whatever I want the app name to be. Obviously not ideal so if you know how to change that, I would love to know. **

## For dev and prod: 
  `npm install`

## For dev:
### Frontend: 
`npm run dev`

HTTP server built on port `3000`

nrepl port on `3333`

I use neovim w/conjure so run your editor's equivalent of: `ConjureShadowSelect app`

This should give you hot reloading on save, including tailwind css changes.
I think something is causing postcss to run even without save sometimes, but it isn't affecting development while I track that down.

### Backend:
To start the server run `clojure -M -m TODO.core`

Start your normal repl like usual. (e.g. For me that's `clj -M:repl` and connect.)

TBH, I'm not quite sure how to have the backend and frontend connected to their respective repls and running at the same time quite yet.

## For prod: 
`npm run release`

`clojure -T:build uber`

`java -jar target/TODO-standalone.jar`
