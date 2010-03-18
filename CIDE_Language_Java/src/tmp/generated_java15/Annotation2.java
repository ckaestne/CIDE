package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Annotation2 extends Annotation {
  public Annotation2(SingleMemberAnnotation singleMemberAnnotation, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SingleMemberAnnotation>("singleMemberAnnotation", singleMemberAnnotation)
    }, firstToken, lastToken);
  }
  public Annotation2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Annotation2(cloneProperties(),firstToken,lastToken);
  }
  public SingleMemberAnnotation getSingleMemberAnnotation() {
    return ((PropertyOne<SingleMemberAnnotation>)getProperty("singleMemberAnnotation")).getValue();
  }
}
