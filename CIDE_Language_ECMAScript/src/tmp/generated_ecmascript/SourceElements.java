package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SourceElements extends GenASTNode {
  public SourceElements(ArrayList<SourceElement> sourceElement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<SourceElement>("sourceElement", sourceElement)
    }, firstToken, lastToken);
  }
  public SourceElements(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SourceElements(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<SourceElement> getSourceElement() {
    return ((PropertyOneOrMore<SourceElement>)getProperty("sourceElement")).getValue();
  }
}
