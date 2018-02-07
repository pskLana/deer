package org.aksw.deer.enrichment.dereferencing;
//
//import com.google.common.collect.ImmutableSet;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Sets;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//import org.aksw.deer.enrichment.AbstractEnrichmentOperator;
//import org.aksw.deer.parameter.JenaResourceConsumingParameter;
//import org.apache.jena.query.Query;
//import org.apache.jena.query.QueryExecution;
//import org.apache.jena.query.QueryExecutionFactory;
//import org.apache.jena.query.QueryFactory;
//import org.apache.jena.query.QuerySolution;
//import org.apache.jena.query.ResultSet;
//import org.apache.jena.rdf.model.Model;
//import org.apache.jena.rdf.model.ModelFactory;
//import org.apache.jena.rdf.model.Property;
//import org.apache.jena.rdf.model.RDFNode;
//import org.apache.jena.rdf.model.Resource;
//import org.apache.jena.rdf.model.ResourceFactory;
//import org.apache.jena.rdf.model.Statement;
//import org.apache.jena.rdf.model.StmtIterator;
//import org.apache.log4j.Logger;
//import org.pf4j.Extension;
//
///**
// * This class includes methods in order to load dataset in Model from file. It
// *         enriches such model with additional information specified by list of targeted predicates
// *         to be added. This is done by following each URI-typed objects in the model and query for
// *         such information in the dereferenced target
// */
//@Extension
@Deprecated
public class OldDereferencingEnrichmentOperator  {
//
//  public static final String INPUT_PROPERTY = "inputproperty";
//  public static final String OUTPUT_PROPERTY = "outputproperty";
//  public static final String USE_BLANK_NODES = "useBlankNodes";
//  public static final String RESOURCE_PREFIX = "resourceprefix";
//  private static final Logger logger = Logger.getLogger(OldDereferencingEnrichmentOperator.class);
//  private static final Property DEFAULT_OUTPUT_PROPERTY = ResourceFactory
//    .createProperty("http://geoknow.org/ontology/relatedTo");
//  private static final String DEFAULT_RESOURCE_PREFIX = "http://dbpedia.org/resource";
//
//  private static final String INPUT_PROPERTY_DESC = "Interesting predicate to enrich the model, e.g. 'predicate1'";
//  private static final String OUTPUT_PROPERTY_DESC =
//    "The output property. By default this parameter is set to " + DEFAULT_OUTPUT_PROPERTY;
//  private static final String USE_BLANK_NODES_DESC = "Use blank node in output dataset. By default, this parameter is set to false";
//  private static final String RESOURCE_PREFIX_Desc =
//    "Resource prefix used for org.aksw.deer.resources.dereferencing resources, by default is set to "
//      + DEFAULT_RESOURCE_PREFIX;
//
//  private Map<RDFNode, Resource> objectsDerefModelAdded = new HashMap<>();
//  private List<Property> inputProperties = new ArrayList<>();
//  private List<Property> outputProperties = new ArrayList<>();
//  private String resourcePrefix = DEFAULT_RESOURCE_PREFIX;
//  private Model localModel = ModelFactory.createDefaultModel();
//
//  private boolean demo = false;
//  private boolean useCache = false;
//  private boolean useBlankNodes = false;
//  private Set<Property> ignoredProperties;
//
//  public OldDereferencingEnrichmentOperator() {
//    super();
//  }
//
//  /**
//   */
//  private void readParameters(Map<String, String> parameters) {
//    for (String key : parameters.keySet()) {
//      if (key.equalsIgnoreCase("useblanknodes")) {
//        useBlankNodes = Boolean.parseBoolean(parameters.get(key));
//      }
//      if (key.equalsIgnoreCase("usecache")) {
//        useCache = Boolean.parseBoolean(parameters.get(key));
//      } else if (key.toLowerCase().startsWith(INPUT_PROPERTY)) {
//        inputProperties.add(ResourceFactory.createProperty(parameters.get(key)));
//      } else if (key.toLowerCase().startsWith(OUTPUT_PROPERTY)) {
//        outputProperties.add(ResourceFactory.createProperty(parameters.get(key)));
//      } else if (key.equalsIgnoreCase(RESOURCE_PREFIX)) {
//        resourcePrefix = parameters.get(key).toString();
//      } else {
//        logger.error("Invalid parameter key: " + key
//          + ", allowed parameters for the org.aksw.deer.resources.dereferencing enrichment are: " + getParameters());
//        logger.error("Exit GeoLift");
//        System.exit(1);
//      }
//    }
//    if (inputProperties.size() == 0) {
//      logger.error(
//        "The " + INPUT_PROPERTY + " is a mandatory parameter(s) for the org.aksw.deer.resources.dereferencing enrichment");
//      logger.error("No " + INPUT_PROPERTY + " provided, Exit GeoLift");
//      System.exit(1);
//    }
//  }
//
//  /**
//   */
//  public List<JenaResourceConsumingParameter> getParameters() {
//    List<String> parameters = new ArrayList<>();
//    parameters.add(INPUT_PROPERTY + "<i>");
//    parameters.add(OUTPUT_PROPERTY + "<i>");
//    parameters.add(USE_BLANK_NODES);
//    //		parameters.add("useCache");
//    return null;
//  }
//
//  @Override
//  public ArityBounds getArityBounds() {
//    return new ArityBoundsImpl(1,1,1,1);
//  }
//
//  /**
//   * Self configuration
//   * Properties(target) - Properties(source)
//   * Find properties in target which are not in source
//   *
//   * @return Map of (key, value) pairs of self configured parameters
//   */
//  public Map<String, String> selfConfig(Model source, Model target) {
//    Map<String, String> parameters = new HashMap<String, String>();
//    Set<Property> properties = getPropertyDifference(source, target);
//    int propertyNr = 1;
//    for (Property p : properties) {
//      parameters.put(INPUT_PROPERTY + propertyNr, p.toString());
//      parameters.put(OUTPUT_PROPERTY + propertyNr, p.toString());
//      propertyNr++;
//    }
//    //		logger.info("Self configuration: " + parameters);
//    if (parameters.size() == 0) {
//      return null;
//    }
//    return parameters;
//  }
//
//  /**
//   * Properties(target) - Properties(source) - Properties(ignoredProperties)
//   *
//   * @return properties which are in target and not in source
//   */
//
//
//  /**
//   */
//  private Set<Property> removeUnwantedProperties(Set<Property> diffProperties) {
//    Set<Property> ignoreProperties = new HashSet<Property>();
////		ignoreProperties.add(OWL.sameAs);
//    for (Property p : ignoreProperties) {
//      diffProperties.remove(p);
//    }
//    return diffProperties;
//  }
//
//  @Override
//  public String getName() {
//    return null;
//  }
//
//  /**
//   * @return model after enrichment This method assigns the localmodel inside the class to the one
//   * given, collects targeted predicates into list, and calls another method
//   * putAdditionalInfoUsingBlankNode to make the enrichment
//   */
//
//  protected List<Model> process() {;
//    Model model = models.get(0);
//    if (model != null) {
//      readParameters(parameters);
//      localModel = localModel.union(model);
//      setPrefixes();
//      if (useBlankNodes) {
//        addAdditionalPropertiesUsingBlankNode(parameters);
//      } else {
//        addAdditionalProperties();
//      }
//    }
//    return Lists.newArrayList(localModel);
//  }
//
//  /**
//   * @return This method retrieves list of values for targeted predicates for a URI-typed object for
//   * each URI-typed object, through content negotiation an open connection is done retrieving its
//   * predicates/values. An iteration is made over targeted predicates. For each predicate list of
//   * statements with the targeted predicate is retrieved and extracting its value in order to be
//   * added to hashmap<predicate,Value>
//   */
//  @SuppressWarnings("unchecked")
//  private HashMap<Property, List<RDFNode>> getURIInfo(RDFNode p) {
//    String uri = p.asResource().getURI();
//    //to store each predicate and its value
//    HashMap<Property, List<RDFNode>> resourceFocusedInfo = new HashMap<Property, List<RDFNode>>();
//
//    if (demo) {    //Deserialize the results if exists (For Demo purpose)
//      if (useCache) {
//        try {
//          HashMap<String, List<String>> ser = new HashMap<String, List<String>>();
//          File file = new File("resourceFocusedInfo.ser");
//          if (file.exists()) {
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
//            ser = (HashMap<String, List<String>>) in.readObject();
//            in.close();
//            // convert every object back from string
//            for (String prop : ser.keySet()) {
//              List<String> l = ser.get(prop);
//              List<RDFNode> nodes = new ArrayList<RDFNode>();
//              for (String n : l) {
//                nodes.add(ResourceFactory.createResource(n));
//              }
//              resourceFocusedInfo.put(ResourceFactory.createProperty(prop), nodes);
//            }
//            return resourceFocusedInfo;
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }
//    }
//
//    //define local model to have the data of the URI and extract focused info through built sparql query
//    List<RDFNode> values = new ArrayList<RDFNode>();
//    try {
//      URLConnection conn = new URL(uri).openConnection();
//      conn.setRequestProperty("Accept", "application/rdf+xml");
//      conn.setRequestProperty("Accept-Language", "en");
//      Model model = ModelFactory.createDefaultModel();
//      InputStream in = conn.getInputStream();
//      model.read(in, null);
//      for (Property inputProperty : inputProperties) {
//        for (Statement st : model
//          .listStatements(model.getResource(uri), inputProperty, (RDFNode) null).toList()) {
//          RDFNode value = st.getObject();
//          if (value.isLiteral()) {
//            if (value.asLiteral().getLanguage().toLowerCase().equals("en") || value.asLiteral()
//              .getLanguage().toLowerCase().equals("")) {
//              values.add(value);
//            }
//          } else {
//            values.add(value);
//          }
//
//        }
//        resourceFocusedInfo.put(inputProperty, values);
//        values = new ArrayList<RDFNode>();//create new list for new predicate
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//    if (demo) { //toRDF the output (for Demo purpose)
//      try {
//        HashMap<String, List<String>> ser = new HashMap<String, List<String>>();
//        FileOutputStream fileOut = new FileOutputStream("resourceFocusedInfo.ser");
//        ObjectOutputStream out = new ObjectOutputStream(fileOut);
//        //convert to Serializabe Strings
//        for (Property prop : resourceFocusedInfo.keySet()) {
//          List<String> l = new ArrayList<String>();
//          for (RDFNode n : resourceFocusedInfo.get(prop)) {
//            l.add(n.toString());
//          }
//          ser.put(prop.toString(), l);
//        }
//        out.writeObject(ser);
//        out.close();
//      } catch (Exception e2) {
//        e2.printStackTrace();
//      }
//    }
//    return resourceFocusedInfo;
//  }
//
//  /**
//   * @param predicates: list of targeted predicates to enrich the model It calls
//   * getTriplesWithObjectsAreURI() method retrieving list of triples in model having URI-typed
//   * objects. For each object of them, it is checked if it is in DBpedia (can be extended later)
//   * then calls getURIInfo() method to dereference the URI-typed object in HashMap and retrieve the
//   * targeted predicates values "if exist", it iterates over the HashMap and add them to the
//   * resources in the model.
//   */
//  private void addAdditionalPropertiesUsingBlankNode(Map<String, String> predicates) {
//    //Map <predicate,value> save each interesting predicate of the URI object
//    Map<Property, List<RDFNode>> resourceInterestingInfoExtension = new HashMap<Property, List<RDFNode>>();
//    //Map<object,objectResourceData> to save each object with its related data resource and be retrieved whenever a URI object data needed to be added for extension
//    Map<RDFNode, Resource> objectFilledResource = new HashMap<RDFNode, Resource>();
//    //Get list of unique URI objects in the data source as http://dbpedia.org/resource/XXXX
//    List<RDFNode> urisObjects = getURIObjects();
//    //Get information for each single distinct objectURI according to interesting predicates
//    logger.info("Number of unique URI object to find extension: " + urisObjects.size());
//    if (urisObjects.size() > 0) {
//      //The object resource that will have each URI object extended data
//      Resource object = null;
//      int count = 1;
//      //For each URI object a resource is created, filled with information,add the object with its data resource into map
//      for (RDFNode uriObject : urisObjects) {
//        logger.info("Predicate " + count++ + " of " + urisObjects.size() + ":" + uriObject);
//        // Create a resource with empty node
//        object = localModel.createResource();
//        //Retrieve all interesting <predicate,object> info. for such URI object
//        resourceInterestingInfoExtension = getURIInfo(uriObject);
//        //Add information to the resource
//        for (Property key : resourceInterestingInfoExtension.keySet()) {
//          //add the new properties to the new triple
//          List<RDFNode> subjects = resourceInterestingInfoExtension.get(key);
//          for (RDFNode subject : subjects) {
//            if (subject.isLiteral()) {
//              object.addProperty(key, subject.asLiteral().toString());
//            } else {
//              object.addProperty(key, subject);
//            }
//          }
//        }
//        //Add to list of object's resource that is filled with information
//        objectFilledResource.put(uriObject, object);
//      }
//    } else {//Otherwise no URI objects to be extended
//      return;
//    }
//    List<Statement> triplesWithURIsObjects = getTriplesWithURIObjects();
//    logger.info("Starting model enriching");
//    if (triplesWithURIsObjects.size() > 0) {
//      Resource object = null;
//      //iterate over each triple to dereference each URI object and add its information to its resource subject
//      for (Statement triple : triplesWithURIsObjects) {
//        //create new triple with empty node as its subject where this subject will be an object of the targeted resource to be extended
//        if (!objectFilledResource.containsKey(triple.getSubject())) {
//          object = objectFilledResource.get(triple.getObject());
//          objectsDerefModelAdded.put(triple.getObject(), object);
//          //Attach the object's resource to this subject
//          Resource resource = localModel.getResource(triple.getSubject().getURI());
//          resource.addProperty(DEFAULT_OUTPUT_PROPERTY, object);
//          resourceInterestingInfoExtension = null;
//        }
//      }
//    }
//  }
//
//  private void addAdditionalProperties() {
//    //Map <predicate,value> save each interesting predicate of the URI object
//    Map<Property, List<RDFNode>> resourceInterestingInfoExtension = new HashMap<Property, List<RDFNode>>();
//    //Map<object,objectResourceData> to save each object with its related data resource and be retrieved whenever a URI object data needed to be added for extension
//    Map<RDFNode, Map<Property, List<RDFNode>>> objectWithInfoAttached = new HashMap<RDFNode, Map<Property, List<RDFNode>>>();
//    //Get list of unique URI objects in the data source as http://dbpedia.org/resource/XXXX
//    List<RDFNode> urisObjects = getURIObjects();
//    //Get information for each single distinct objectURI according to interesting predicates
//    logger.info("Number of unique URI object to find extension: " + urisObjects.size());
//    if (urisObjects.size() > 0) {
//      //For each unique URI object, its predicate-value pairs are retrieved then add them attached to their object in a map
//      for (RDFNode uriObject : urisObjects) {
//        //Retrieve all interesting <predicate,object> info. for current URI object
//        resourceInterestingInfoExtension = getURIInfo(uriObject);
//        //Add retrieved predicate-value pair attached to the object in the map
//        objectWithInfoAttached
//          .put(uriObject, resourceInterestingInfoExtension);//enriched list of objects
//      }
//    } else {  //Otherwise no URI objects to be extended
//      return;
//    }
//
//    List<Statement> triplesWithURIsObjects = getTriplesWithURIObjects();
//    logger.info("Starting model enriching");
//    if (triplesWithURIsObjects.size() > 0) {
//      //iterate over each triple to add each URI object information to its resource subject
//      for (Statement triple : triplesWithURIsObjects) {
//        //put a hand over the required subject
//        Resource enrichedResource = (Resource) triple.getObject();
//        //for the subject's related object in the enriched list get the related predicate-value pairs
//        Map<Property, List<RDFNode>> objectPredicateValuePairs = objectWithInfoAttached
//          .get(triple.getObject());
//        int i = 0;
//        for (Property predicate : objectPredicateValuePairs.keySet()) {
//          for (RDFNode value : objectPredicateValuePairs.get(predicate)) {
//            Property outputProperty =
//              (i < outputProperties.size()) ? outputProperties.get(i) : DEFAULT_OUTPUT_PROPERTY;
//            if (value.isLiteral()) {
//              localModel.add(enrichedResource, outputProperty, value.asLiteral().toString());
//              logger.info(
//                "Triple found: <" + enrichedResource + "> <" + outputProperty + "> \"" + value
//                  .toString() + "\"");
//            } else {
//              localModel.add(enrichedResource, outputProperty, value);
//              logger.info(
//                "Triple found: <" + enrichedResource + "> <" + outputProperty + "> <" + value
//                  + ">");
//            }
//          }
//          i++;
//        }
//      }
//    }
//  }
//
//  /**
//   * @return list of triples having URI-typed objects it queries  the model for all its URI-typed
//   * objects as it check every object if it is a resource (URI). if so then add them to the list.
//   */
//  @SuppressWarnings("unchecked")
//  private List<Statement> getTriplesWithURIObjects() {
//    List<Statement> triplesWithURIObjects = new ArrayList<Statement>();
//
//    if (demo) { //Deserialize the results if exists (For Demo purpose)
//      if (useCache) {
//        try {
//          List<String> ser = new ArrayList<String>();
//          File file = new File("triplesWithURIObjects.ser");
//          if (file.exists()) {
//            ObjectInputStream in;
//            in = new ObjectInputStream(new FileInputStream(file));
//            ser = (List<String>) in.readObject();
//            in.close();
//            // convert every object back from string
//            for (String st : ser) {
//              triplesWithURIObjects.add(ResourceFactory.createStatement(
//                ResourceFactory.createResource(st.split(" ")[0]),
//                ResourceFactory.createProperty(st.split(" ")[1]),
//                ResourceFactory.createResource(st.split(" ")[2])));
//            }
//            return triplesWithURIObjects;
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }
//    }
//
//    //create a query to retrieve URIs objects
//    String queryString =
//      "SELECT * " +
//        "WHERE { ?s ?p ?o . FILTER (isURI(?o)) . " +
//        "FILTER (STRSTARTS(STR(?o), \"" + resourcePrefix + "\"))}";
//    Query query = QueryFactory.create(queryString);
//    QueryExecution exec = QueryExecutionFactory.create(query, localModel);
//    ResultSet rs = exec.execSelect();
//    while (rs.hasNext()) {
//      QuerySolution sol = rs.next();
//      RDFNode s = sol.get("?s");
//      RDFNode p = sol.get("?p");
//      RDFNode o = sol.get("?o");
//      Statement triple = ResourceFactory
//        .createStatement(s.asResource(), ResourceFactory.createProperty(p.toString()), o);
//      triplesWithURIObjects.add(triple);
//    }
//
//    if (demo) {    //toRDF the output (for Demo purpose)
//      try {
//        FileOutputStream fileOut = new FileOutputStream("triplesWithURIObjects.ser");
//        ObjectOutputStream out = new ObjectOutputStream(fileOut);
//        List<String> l = new ArrayList<String>();
//        for (Statement s : triplesWithURIObjects) {
//          l.add(s.getSubject() + " " + s.getPredicate() + " " + s.getObject());
//        }
//        out.writeObject(l);
//        out.close();
//      } catch (Exception e2) {
//        e2.printStackTrace();
//      }
//    }
//
//    return triplesWithURIObjects;
//  }
//
//  @SuppressWarnings("unchecked")
//  private List<RDFNode> getURIObjects() {
//    List<RDFNode> objectsURIs = new ArrayList<RDFNode>();
//    if (demo) {  //Deserialize the results if exists (For Demo purpose)
//      if (useCache) {
//        try {
//          List<String> ser = new ArrayList<String>();
//          File file = new File("URIObjects.ser");
//          if (file.exists()) {
//            ObjectInputStream in;
//            in = new ObjectInputStream(new FileInputStream(file));
//            ser = (List<String>) in.readObject();
//            in.close();
//            // convert every object back from string
//            for (String n : ser) {
//              objectsURIs.add(ResourceFactory.createResource(n));
//            }
//            return objectsURIs;
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }
//    }
//
//    //create a query to retrieve URIs objects
//    String queryString =
//      "SELECT * " +
//        "WHERE { ?s ?p ?o . FILTER (isURI(?o)) . " +
//        "FILTER (STRSTARTS(STR(?o), \"" + resourcePrefix + "\"))}";
//
//    Query query = QueryFactory.create(queryString);
//    QueryExecution exec = QueryExecutionFactory.create(query, localModel);
//    ResultSet rs = exec.execSelect();
//    while (rs.hasNext()) {
//      QuerySolution sol = rs.next();
//      RDFNode object = sol.get("?o");
//      objectsURIs.add(object);
//    }
//    if (demo) { //toRDF the output (for Demo purpose)
//      try {
//        FileOutputStream fileOut = new FileOutputStream("URIObjects.ser");
//        ObjectOutputStream out = new ObjectOutputStream(fileOut);
//        //convert to Serializabe Strings
//        List<String> l = new ArrayList<String>();
//        for (RDFNode n : objectsURIs) {
//          l.add(n.toString());
//        }
//        out.writeObject(l);
//        out.close();
//      } catch (Exception e2) {
//        e2.printStackTrace();
//      }
//    }
//
//    return objectsURIs;
//  }
//
//  private void setPrefixes() {
//    String gn = "http://www.geonames.org/ontology#";
//    localModel.setNsPrefix("gn", gn);
//  }
//
}
