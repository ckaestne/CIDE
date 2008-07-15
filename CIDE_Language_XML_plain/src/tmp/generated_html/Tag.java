package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Tag extends GenASTNode {
  public Tag(ASTStringNode tag_name, AttributeList attributeList, ASTStringNode slash, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("tag_name", tag_name),
      new PropertyOne<AttributeList>("attributeList", attributeList),
      new PropertyZeroOrOne<ASTStringNode>("slash", slash)
    }, firstToken, lastToken);
  }
  public Tag(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Tag(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getTag_name() {
    return ((PropertyOne<ASTStringNode>)getProperty("tag_name")).getValue();
  }
  public AttributeList getAttributeList() {
    return ((PropertyOne<AttributeList>)getProperty("attributeList")).getValue();
  }
  public ASTStringNode getSlash() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("slash")).getValue();
  }
}
