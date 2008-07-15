package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Annotation3 extends Annotation {
  public Annotation3(MarkerAnnotation markerAnnotation, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MarkerAnnotation>("markerAnnotation", markerAnnotation)
    }, firstToken, lastToken);
  }
  public Annotation3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Annotation3(cloneProperties(),firstToken,lastToken);
  }
  public MarkerAnnotation getMarkerAnnotation() {
    return ((PropertyOne<MarkerAnnotation>)getProperty("markerAnnotation")).getValue();
  }
}
