# clojure-app-template
## Full stack Clojure/Clojurescript app
  ### backend: deps.edn, jetty, reitit, aero 
  
  I've commented out some dependencies in `deps.edn`, mostly database stuff. You can just delete that if not needed. Integrant to be added shortly.
  
  ### frontend: shadow-cljs, reagent, re-frame, re-frame-10x, tailwindcss
  
  ### deployed through dockerfile 

Demo is currently deployed and running live through `render.com` at: https://clojure-demo-app.onrender.com/ 

With a simple "Hello World" component I get 100% scores across the board on Lighthouse.

The free tier allows for 512mb and this demo seems to use up to ~170mb for just the "Hello World" according to `render.com`'s metrics but I haven't looked into too much yet.

The dockerfile should allow it to be used elsewhere like `fly.io`, `railway.app`, and `heroku` but I haven't tested.

** I don't know how to actually create an official template yet so I manually have to change every occurrence of `TODO` in the repo 
to whatever I want the app name to be. Obviously not ideal so if you know how to change that, I would love to know. **

## For dev and prod: 
  `npm install`

## For dev:
  `npm run dev`

Right now this runs the app serve on port `4000` (which is what `render.com` wants) but also
runs shadow-cljs's HTTP server built on port `3000`. I'll probably tweak this workflow but 
when I just want to work client side and don't need the app server I just run 
`shadow-cljs watch app` instead of the `npm` script

nrepl port on `7002`

I use neovim w/conjure so run your editor's equivalent of: `ConjureShadowSelect app`

This should give you hot reloading on save, including tailwind css changes.
For dev, I switched to using tailwind's cdn as I was getting sever performance problems 
and other issues when using postcss with shadow-cljs hot reloading. Now it all works 
great and the cdn will not be included in the production build (which still uses postcss
for treeshaking, etc.) One hacky aspect of this fix is that I have to have the 
`tailwind.config.js` file at both the root level and again copied into the `resources` 
folder. I am currently looking into a fix on that. 

### Backend:
To start the server run `clojure -M -m server.core`

Start your normal repl like usual. (e.g. For me that's `clj -M:repl` and connect.)

TBH, I'm not quite sure how to have the backend and frontend connected to their respective repls and running at the same time quite yet.

## For prod: 
`npm run release`

`java -jar target/TODO.jar`
