package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fixed_parameterEnd extends GenASTNode {
  public fixed_parameterEnd(attributes attributes, formal_parameter_listEndInt formal_parameter_listEndInt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<attributes>("attributes", attributes),
      new PropertyOne<formal_parameter_listEndInt>("formal_parameter_listEndInt", formal_parameter_listEndInt)
    }, firstToken, lastToken);
  }
  public fixed_parameterEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fixed_parameterEnd(cloneProperties(),firstToken,lastToken);
  }
  public attributes getAttributes() {
    return ((PropertyZeroOrOne<attributes>)getProperty("attributes")).getValue();
  }
  public formal_parameter_listEndInt getFormal_parameter_listEndInt() {
    return ((PropertyOne<formal_parameter_listEndInt>)getProperty("formal_parameter_listEndInt")).getValue();
  }
}
