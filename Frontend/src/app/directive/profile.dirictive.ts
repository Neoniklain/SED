import {
    ComponentFactoryResolver, ComponentRef,
    Directive,
    ElementRef,
    HostListener,
    Injector, Input, ReflectiveInjector,
    Renderer2,
    ViewContainerRef
} from "@angular/core";
import {ProfileComponent} from "../components/shared/profile/profile";
import {User} from "../models/account/user.model";

@Directive({
    selector: "[userCard]"
})

export class ProfileDirective {

    @Input() userCard: User;

    constructor(private element: ElementRef,
                private renderer: Renderer2,
                private injector: Injector,
                private resolver: ComponentFactoryResolver,
                private vcr: ViewContainerRef) {
        this.element.nativeElement.style.color = "#008080";
    }

    private componentRef: ComponentRef<ProfileComponent>;

    /*@HostListener('click')
    onclick() {
        // задел на будущее, переход на страничку профиля
    }*/

    @HostListener('mouseenter')
    mouseenter() {
        if (this.componentRef ) return;
        const factory = this.resolver.resolveComponentFactory(ProfileComponent);
        const injector = ReflectiveInjector.resolveAndCreate([
            {
                provide: 'profileConfig',
                useValue: {
                    host: this.element.nativeElement
                }
            }
        ]);
        this.element.nativeElement.style.cursor = "pointer";
        this.element.nativeElement.style.textDecoration = "underline";
        this.element.nativeElement.style.borderBottom = "none !important";
        this.componentRef = this.vcr.createComponent(factory, 0, injector);
        this.componentRef.instance.user = this.userCard;
    }

    @HostListener('mouseout')
    mouseout() {
        this.element.nativeElement.style.textDecoration = "none";
        this.element.nativeElement.style.borderBottom = "none !important";
        this.destroy();
    }

    destroy() {
        this.componentRef && this.componentRef.destroy();
        this.componentRef = null;
    }

    ngOnDestroy() {
        this.destroy();
    }
}














