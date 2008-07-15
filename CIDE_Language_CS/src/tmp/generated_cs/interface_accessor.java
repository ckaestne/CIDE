package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class interface_accessor extends GenASTNode {
  public interface_accessor(attributes attributes, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<attributes>("attributes", attributes),
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public interface_accessor(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new interface_accessor(cloneProperties(),firstToken,lastToken);
  }
  public attributes getAttributes() {
    return ((PropertyZeroOrOne<attributes>)getProperty("attributes")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
