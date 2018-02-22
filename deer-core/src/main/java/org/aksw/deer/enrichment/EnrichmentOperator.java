package org.aksw.deer.enrichment;

import org.aksw.deer.parameter.ParameterMap;
import org.aksw.deer.parameter.ParametrizedPlugin;
import org.apache.jena.rdf.model.Model;
import org.jetbrains.annotations.NotNull;
import org.pf4j.ExtensionPoint;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * An Enrichment Operator.
 * <p>
 * An enrichment operator is an atomic operator on a list of RDF models, yielding a list of RDF models.
 * Its arity
 */
public interface EnrichmentOperator extends ExtensionPoint, UnaryOperator<Model>, ParametrizedPlugin {

  class DegreeBounds {
    private final int minIn;
    private final int maxIn;
    private final int minOut;
    private final int maxOut;

    public DegreeBounds(int minIn, int maxIn, int minOut, int maxOut) {
      this.minIn = minIn;
      this.maxIn = maxIn;
      this.minOut = minOut;
      this.maxOut = maxOut;
    }

    public int minIn() {
      return minIn;
    }

    public int maxIn() {
      return maxIn;
    }

    public int minOut() {
      return minOut;
    }

    public int maxOut() {
      return maxOut;
    }

  }

  DegreeBounds getDegreeBounds();

  int getInDegree();

  int getOutDegree();

  void init(@NotNull ParameterMap parameterMap, int inArity, int outArity);

  @NotNull
  ParameterMap selfConfig(Model source, Model target);

  @NotNull
  ParameterMap getParameterMap();

  Model apply(Model model);

  List<Model> apply(List<Model> models);

  void init(@NotNull ParameterMap params);

}