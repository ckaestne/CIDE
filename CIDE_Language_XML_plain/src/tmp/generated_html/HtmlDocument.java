package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class HtmlDocument extends GenASTNode implements ISourceFile {
  public HtmlDocument(ElementSequence elementSequence, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ElementSequence>("elementSequence", elementSequence),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public HtmlDocument(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new HtmlDocument(cloneProperties(),firstToken,lastToken);
  }
  public ElementSequence getElementSequence() {
    return ((PropertyOne<ElementSequence>)getProperty("elementSequence")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
