package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class accessor_declaration extends GenASTNode {
  public accessor_declaration(attributes attributes, ASTStringNode identifier, body body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<attributes>("attributes", attributes),
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<body>("body", body)
    }, firstToken, lastToken);
  }
  public accessor_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new accessor_declaration(cloneProperties(),firstToken,lastToken);
  }
  public attributes getAttributes() {
    return ((PropertyZeroOrOne<attributes>)getProperty("attributes")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public body getBody() {
    return ((PropertyOne<body>)getProperty("body")).getValue();
  }
}
