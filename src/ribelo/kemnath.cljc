(ns ribelo.kemnath
  (:refer-clojure :exclude [min max]))

#?(:clj (set! *warn-on-reflection* true))
#?(:clj (set! *unchecked-math* :warn-on-boxed))

(def ^:const PI
  #?(:clj  Math/PI
     :cljs js/Math.PI))

(def ^:const E
  #?(:clj  Math/E
     :cljs js/Math.E))

(def ^:const MAX-DOUBLE
  #?(:clj  Double/MAX_VALUE
     :cljs js/Number.MAX_VALUE))

(def ^:const MIN-DOUBLE
  #?(:clj  Double/MIN_VALUE
     :cljs js/Number.MIN_VALUE))

(def ^:const MAX-LONG
  #?(:clj  Long/MAX_VALUE
     :cljs js/Number.MAX_VALUE))

(def ^:const MIN-LONG
  #?(:clj  Long/MIN_VALUE
     :cljs js/Number.MIN_VALUE))

(defn max
  ^double [^double x ^double y]
  #?(:clj  (Math/max x y)
     :cljs (js/Math.max x y)))

(defn min
  ^double [^double x ^double y]
  #?(:clj  (Math/min x y)
     :cljs (js/Math.min x y)))

(defn abs
  ^double [^double x]
  #?(:clj  (Math/abs x)
     :cljs (js/Math.abs x)))

(defn sqrt
  ^double [^double x]
  #?(:clj  (Math/sqrt x)
     :cljs (js/Math.sqrt x)))

(defn sq
  ^double [^double x]
  (* x x))

(defn pow
  ^double [^double x ^double n]
  #?(:clj  (Math/pow x n)
     :cljs (js/Math.pow x n)))

(defn root
  ^double [^double x ^double n]
  (pow x (/ 1 n)))

(defn log
  ^double [^double x]
  #?(:clj  (Math/log x)
     :cljs (js/Math.log x)))

(defn log1p
  ^double [^double x]
  #?(:clj  (Math/log (inc x))
     :cljs (js/Math.log (inc x))))

(defn exp
  ^double [^double x]
  #?(:clj  (Math/exp x)
     :cljs (js/Math.exp x)))

(defn cos
  ^double [^double x]
  #?(:clj  (Math/cos x)
     :cljs (js/Math.cos x)))

(defn sin
  ^double [^double x]
  #?(:clj  (Math/sin x)
     :cljs (js/Math.sin x)))

(defn ceil
  (^double [^double x]
   #?(:clj  (double (Math/ceil x))
      :cljs (js/Math.ceil x)))
  (^double [^double x ^long nplaces]
   (if (< 1 nplaces)
     (let [modifier (pow 10.0 (double nplaces))
           x*       (* x modifier)
           rounded  (ceil x*)]
       (/ rounded modifier))
     (ceil x))))

(defn floor
  (^double [^double x]
   #?(:clj  (double (Math/floor x))
      :cljs (js/Math.floor x)))
  (^double [^double x ^long nplaces]
   (if (< 1 nplaces)
     (let [modifier (pow 10.0 (double nplaces))
           x*       (* x modifier)
           rounded  (floor x*)]
       (/ rounded modifier))
     (floor x))))

(defn equal [^double x ^double y ^double e]
  (<= (abs (- y x)) e))

(defn sign
  ^long [^double x]
  (cond (> x 0.0)  1
        (< x 0.0) -1
        :else      0))

(defn round
  (^double [^double x]
   #?(:clj (double (Math/round x))
      :cljs (js/Math.round x)))
  (^double [^double x ^long nplaces]
   (if (< 1 nplaces)
     (let [modifier (pow 10.0 (double nplaces))
           x*       (* x modifier)
           rounded  #?(:clj (Math/round x*)
                       :cljs (js/Math.round x*))]
       (/ rounded modifier))
     (round x))))

(defn round2 ^double [^double x]
  #?(:clj  (/ (Math/round    (* x 100.0)) 100.0)
     :cljs (/ (js/Math.round (* x 100.0)) 100.0)))
