package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Annotation1 extends Annotation {
  public Annotation1(NormalAnnotation normalAnnotation, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<NormalAnnotation>("normalAnnotation", normalAnnotation)
    }, firstToken, lastToken);
  }
  public Annotation1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Annotation1(cloneProperties(),firstToken,lastToken);
  }
  public NormalAnnotation getNormalAnnotation() {
    return ((PropertyOne<NormalAnnotation>)getProperty("normalAnnotation")).getValue();
  }
}
