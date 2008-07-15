package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fixed_statement extends GenASTNode {
  public fixed_statement(type type, fixed_pointer_declarators fixed_pointer_declarators, embedded_statement embedded_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyOne<fixed_pointer_declarators>("fixed_pointer_declarators", fixed_pointer_declarators),
      new PropertyOne<embedded_statement>("embedded_statement", embedded_statement)
    }, firstToken, lastToken);
  }
  public fixed_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fixed_statement(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public fixed_pointer_declarators getFixed_pointer_declarators() {
    return ((PropertyOne<fixed_pointer_declarators>)getProperty("fixed_pointer_declarators")).getValue();
  }
  public embedded_statement getEmbedded_statement() {
    return ((PropertyOne<embedded_statement>)getProperty("embedded_statement")).getValue();
  }
}
