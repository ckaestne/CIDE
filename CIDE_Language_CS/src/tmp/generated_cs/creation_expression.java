package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class creation_expression extends GenASTNode {
  public creation_expression(non_array_type non_array_type, creation_expressionPostFix creation_expressionPostFix, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<non_array_type>("non_array_type", non_array_type),
      new PropertyOne<creation_expressionPostFix>("creation_expressionPostFix", creation_expressionPostFix)
    }, firstToken, lastToken);
  }
  public creation_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new creation_expression(cloneProperties(),firstToken,lastToken);
  }
  public non_array_type getNon_array_type() {
    return ((PropertyOne<non_array_type>)getProperty("non_array_type")).getValue();
  }
  public creation_expressionPostFix getCreation_expressionPostFix() {
    return ((PropertyOne<creation_expressionPostFix>)getProperty("creation_expressionPostFix")).getValue();
  }
}
