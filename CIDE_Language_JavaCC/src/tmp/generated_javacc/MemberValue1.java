package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberValue1 extends MemberValue {
  public MemberValue1(Annotation annotation, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Annotation>("annotation", annotation)
    }, firstToken, lastToken);
  }
  public MemberValue1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberValue1(cloneProperties(),firstToken,lastToken);
  }
  public Annotation getAnnotation() {
    return ((PropertyOne<Annotation>)getProperty("annotation")).getValue();
  }
}
