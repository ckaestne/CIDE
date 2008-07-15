package tmp.generated_xml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Element1 extends Element {
  public Element1(EmptyElemTag emptyElemTag, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EmptyElemTag>("emptyElemTag", emptyElemTag)
    }, firstToken, lastToken);
  }
  public Element1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Element1(cloneProperties(),firstToken,lastToken);
  }
  public EmptyElemTag getEmptyElemTag() {
    return ((PropertyOne<EmptyElemTag>)getProperty("emptyElemTag")).getValue();
  }
}
